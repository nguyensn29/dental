/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import OrderDetailsComponent from '@/entities/order-details/order-details.vue';
import OrderDetailsClass from '@/entities/order-details/order-details.component';
import OrderDetailsService from '@/entities/order-details/order-details.service';
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
  describe('OrderDetails Management Component', () => {
    let wrapper: Wrapper<OrderDetailsClass>;
    let comp: OrderDetailsClass;
    let orderDetailsServiceStub: SinonStubbedInstance<OrderDetailsService>;

    beforeEach(() => {
      orderDetailsServiceStub = sinon.createStubInstance<OrderDetailsService>(OrderDetailsService);
      orderDetailsServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<OrderDetailsClass>(OrderDetailsComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          orderDetailsService: () => orderDetailsServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      orderDetailsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllOrderDetailss();
      await comp.$nextTick();

      // THEN
      expect(orderDetailsServiceStub.retrieve.called).toBeTruthy();
      expect(comp.orderDetails[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      orderDetailsServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(orderDetailsServiceStub.retrieve.callCount).toEqual(1);

      comp.removeOrderDetails();
      await comp.$nextTick();

      // THEN
      expect(orderDetailsServiceStub.delete.called).toBeTruthy();
      expect(orderDetailsServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
