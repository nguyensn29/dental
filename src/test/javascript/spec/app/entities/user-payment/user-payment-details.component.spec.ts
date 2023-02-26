/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UserPaymentDetailComponent from '@/entities/user-payment/user-payment-details.vue';
import UserPaymentClass from '@/entities/user-payment/user-payment-details.component';
import UserPaymentService from '@/entities/user-payment/user-payment.service';
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
  describe('UserPayment Management Detail Component', () => {
    let wrapper: Wrapper<UserPaymentClass>;
    let comp: UserPaymentClass;
    let userPaymentServiceStub: SinonStubbedInstance<UserPaymentService>;

    beforeEach(() => {
      userPaymentServiceStub = sinon.createStubInstance<UserPaymentService>(UserPaymentService);

      wrapper = shallowMount<UserPaymentClass>(UserPaymentDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { userPaymentService: () => userPaymentServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUserPayment = { id: 123 };
        userPaymentServiceStub.find.resolves(foundUserPayment);

        // WHEN
        comp.retrieveUserPayment(123);
        await comp.$nextTick();

        // THEN
        expect(comp.userPayment).toBe(foundUserPayment);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserPayment = { id: 123 };
        userPaymentServiceStub.find.resolves(foundUserPayment);

        // WHEN
        comp.beforeRouteEnter({ params: { userPaymentId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.userPayment).toBe(foundUserPayment);
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
