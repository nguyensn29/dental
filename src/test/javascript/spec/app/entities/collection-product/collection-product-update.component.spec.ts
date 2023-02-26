/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CollectionProductUpdateComponent from '@/entities/collection-product/collection-product-update.vue';
import CollectionProductClass from '@/entities/collection-product/collection-product-update.component';
import CollectionProductService from '@/entities/collection-product/collection-product.service';

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
  describe('CollectionProduct Management Update Component', () => {
    let wrapper: Wrapper<CollectionProductClass>;
    let comp: CollectionProductClass;
    let collectionProductServiceStub: SinonStubbedInstance<CollectionProductService>;

    beforeEach(() => {
      collectionProductServiceStub = sinon.createStubInstance<CollectionProductService>(CollectionProductService);

      wrapper = shallowMount<CollectionProductClass>(CollectionProductUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          collectionProductService: () => collectionProductServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.collectionProduct = entity;
        collectionProductServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(collectionProductServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.collectionProduct = entity;
        collectionProductServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(collectionProductServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCollectionProduct = { id: 123 };
        collectionProductServiceStub.find.resolves(foundCollectionProduct);
        collectionProductServiceStub.retrieve.resolves([foundCollectionProduct]);

        // WHEN
        comp.beforeRouteEnter({ params: { collectionProductId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.collectionProduct).toBe(foundCollectionProduct);
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
