/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CollectionUpdateComponent from '@/entities/collection/collection-update.vue';
import CollectionClass from '@/entities/collection/collection-update.component';
import CollectionService from '@/entities/collection/collection.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Collection Management Update Component', () => {
    let wrapper: Wrapper<CollectionClass>;
    let comp: CollectionClass;
    let collectionServiceStub: SinonStubbedInstance<CollectionService>;

    beforeEach(() => {
      collectionServiceStub = sinon.createStubInstance<CollectionService>(CollectionService);

      wrapper = shallowMount<CollectionClass>(CollectionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          collectionService: () => collectionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.collection = entity;
        collectionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(collectionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.collection = entity;
        collectionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(collectionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCollection = { id: 123 };
        collectionServiceStub.find.resolves(foundCollection);
        collectionServiceStub.retrieve.resolves([foundCollection]);

        // WHEN
        comp.beforeRouteEnter({ params: { collectionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.collection).toBe(foundCollection);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
