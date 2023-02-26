/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ProductUserComponent from '@/entities/product-user/product-user.vue';
import ProductUserClass from '@/entities/product-user/product-user.component';
import ProductUserService from '@/entities/product-user/product-user.service';
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
  describe('ProductUser Management Component', () => {
    let wrapper: Wrapper<ProductUserClass>;
    let comp: ProductUserClass;
    let productUserServiceStub: SinonStubbedInstance<ProductUserService>;

    beforeEach(() => {
      productUserServiceStub = sinon.createStubInstance<ProductUserService>(ProductUserService);
      productUserServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ProductUserClass>(ProductUserComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          productUserService: () => productUserServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      productUserServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllProductUsers();
      await comp.$nextTick();

      // THEN
      expect(productUserServiceStub.retrieve.called).toBeTruthy();
      expect(comp.productUsers[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      productUserServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(productUserServiceStub.retrieve.callCount).toEqual(1);

      comp.removeProductUser();
      await comp.$nextTick();

      // THEN
      expect(productUserServiceStub.delete.called).toBeTruthy();
      expect(productUserServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
