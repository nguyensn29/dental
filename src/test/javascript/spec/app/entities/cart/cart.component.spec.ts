/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CartComponent from '@/entities/cart/cart.vue';
import CartClass from '@/entities/cart/cart.component';
import CartService from '@/entities/cart/cart.service';
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
  describe('Cart Management Component', () => {
    let wrapper: Wrapper<CartClass>;
    let comp: CartClass;
    let cartServiceStub: SinonStubbedInstance<CartService>;

    beforeEach(() => {
      cartServiceStub = sinon.createStubInstance<CartService>(CartService);
      cartServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CartClass>(CartComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          cartService: () => cartServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      cartServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCarts();
      await comp.$nextTick();

      // THEN
      expect(cartServiceStub.retrieve.called).toBeTruthy();
      expect(comp.carts[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      cartServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(cartServiceStub.retrieve.callCount).toEqual(1);

      comp.removeCart();
      await comp.$nextTick();

      // THEN
      expect(cartServiceStub.delete.called).toBeTruthy();
      expect(cartServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
