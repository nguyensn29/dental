/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CollectionDetailComponent from '@/entities/collection/collection-details.vue';
import CollectionClass from '@/entities/collection/collection-details.component';
import CollectionService from '@/entities/collection/collection.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Collection Management Detail Component', () => {
    let wrapper: Wrapper<CollectionClass>;
    let comp: CollectionClass;
    let collectionServiceStub: SinonStubbedInstance<CollectionService>;

    beforeEach(() => {
      collectionServiceStub = sinon.createStubInstance<CollectionService>(CollectionService);

      wrapper = shallowMount<CollectionClass>(CollectionDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { collectionService: () => collectionServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCollection = { id: 123 };
        collectionServiceStub.find.resolves(foundCollection);

        // WHEN
        comp.retrieveCollection(123);
        await comp.$nextTick();

        // THEN
        expect(comp.collection).toBe(foundCollection);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCollection = { id: 123 };
        collectionServiceStub.find.resolves(foundCollection);

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
