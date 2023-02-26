/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CollectionComponent from '@/entities/collection/collection.vue';
import CollectionClass from '@/entities/collection/collection.component';
import CollectionService from '@/entities/collection/collection.service';
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
  describe('Collection Management Component', () => {
    let wrapper: Wrapper<CollectionClass>;
    let comp: CollectionClass;
    let collectionServiceStub: SinonStubbedInstance<CollectionService>;

    beforeEach(() => {
      collectionServiceStub = sinon.createStubInstance<CollectionService>(CollectionService);
      collectionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CollectionClass>(CollectionComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          collectionService: () => collectionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      collectionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCollections();
      await comp.$nextTick();

      // THEN
      expect(collectionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.collections[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      collectionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(collectionServiceStub.retrieve.callCount).toEqual(1);

      comp.removeCollection();
      await comp.$nextTick();

      // THEN
      expect(collectionServiceStub.delete.called).toBeTruthy();
      expect(collectionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
