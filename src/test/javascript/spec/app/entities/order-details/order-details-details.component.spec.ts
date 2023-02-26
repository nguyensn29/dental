/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import OrderDetailsDetailComponent from '@/entities/order-details/order-details-details.vue';
import OrderDetailsClass from '@/entities/order-details/order-details-details.component';
import OrderDetailsService from '@/entities/order-details/order-details.service';
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
  describe('OrderDetails Management Detail Component', () => {
    let wrapper: Wrapper<OrderDetailsClass>;
    let comp: OrderDetailsClass;
    let orderDetailsServiceStub: SinonStubbedInstance<OrderDetailsService>;

    beforeEach(() => {
      orderDetailsServiceStub = sinon.createStubInstance<OrderDetailsService>(OrderDetailsService);

      wrapper = shallowMount<OrderDetailsClass>(OrderDetailsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { orderDetailsService: () => orderDetailsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundOrderDetails = { id: 123 };
        orderDetailsServiceStub.find.resolves(foundOrderDetails);

        // WHEN
        comp.retrieveOrderDetails(123);
        await comp.$nextTick();

        // THEN
        expect(comp.orderDetails).toBe(foundOrderDetails);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundOrderDetails = { id: 123 };
        orderDetailsServiceStub.find.resolves(foundOrderDetails);

        // WHEN
        comp.beforeRouteEnter({ params: { orderDetailsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.orderDetails).toBe(foundOrderDetails);
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
