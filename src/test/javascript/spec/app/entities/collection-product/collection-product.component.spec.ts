/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CollectionProductComponent from '@/entities/collection-product/collection-product.vue';
import CollectionProductClass from '@/entities/collection-product/collection-product.component';
import CollectionProductService from '@/entities/collection-product/collection-product.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('CollectionProduct Management Component', () => {
    let wrapper: Wrapper<CollectionProductClass>;
    let comp: CollectionProductClass;
    let collectionProductServiceStub: SinonStubbedInstance<CollectionProductService>;

    beforeEach(() => {
      collectionProductServiceStub = sinon.createStubInstance<CollectionProductService>(CollectionProductService);
      collectionProductServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CollectionProductClass>(CollectionProductComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          collectionProductService: () => collectionProductServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      collectionProductServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCollectionProducts();
      await comp.$nextTick();

      // THEN
      expect(collectionProductServiceStub.retrieve.called).toBeTruthy();
      expect(comp.collectionProducts[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      collectionProductServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(collectionProductServiceStub.retrieve.callCount).toEqual(1);

      comp.removeCollectionProduct();
      await comp.$nextTick();

      // THEN
      expect(collectionProductServiceStub.delete.called).toBeTruthy();
      expect(collectionProductServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
