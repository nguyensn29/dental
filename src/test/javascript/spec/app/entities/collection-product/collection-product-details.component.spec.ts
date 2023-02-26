/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CollectionProductDetailComponent from '@/entities/collection-product/collection-product-details.vue';
import CollectionProductClass from '@/entities/collection-product/collection-product-details.component';
import CollectionProductService from '@/entities/collection-product/collection-product.service';
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
  describe('CollectionProduct Management Detail Component', () => {
    let wrapper: Wrapper<CollectionProductClass>;
    let comp: CollectionProductClass;
    let collectionProductServiceStub: SinonStubbedInstance<CollectionProductService>;

    beforeEach(() => {
      collectionProductServiceStub = sinon.createStubInstance<CollectionProductService>(CollectionProductService);

      wrapper = shallowMount<CollectionProductClass>(CollectionProductDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { collectionProductService: () => collectionProductServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCollectionProduct = { id: 123 };
        collectionProductServiceStub.find.resolves(foundCollectionProduct);

        // WHEN
        comp.retrieveCollectionProduct(123);
        await comp.$nextTick();

        // THEN
        expect(comp.collectionProduct).toBe(foundCollectionProduct);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCollectionProduct = { id: 123 };
        collectionProductServiceStub.find.resolves(foundCollectionProduct);

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
