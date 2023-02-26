/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import OrderDetailsUpdateComponent from '@/entities/order-details/order-details-update.vue';
import OrderDetailsClass from '@/entities/order-details/order-details-update.component';
import OrderDetailsService from '@/entities/order-details/order-details.service';

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
  describe('OrderDetails Management Update Component', () => {
    let wrapper: Wrapper<OrderDetailsClass>;
    let comp: OrderDetailsClass;
    let orderDetailsServiceStub: SinonStubbedInstance<OrderDetailsService>;

    beforeEach(() => {
      orderDetailsServiceStub = sinon.createStubInstance<OrderDetailsService>(OrderDetailsService);

      wrapper = shallowMount<OrderDetailsClass>(OrderDetailsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          orderDetailsService: () => orderDetailsServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.orderDetails = entity;
        orderDetailsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderDetailsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.orderDetails = entity;
        orderDetailsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(orderDetailsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundOrderDetails = { id: 123 };
        orderDetailsServiceStub.find.resolves(foundOrderDetails);
        orderDetailsServiceStub.retrieve.resolves([foundOrderDetails]);

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
