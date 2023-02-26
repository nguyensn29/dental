/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UserPaymentUpdateComponent from '@/entities/user-payment/user-payment-update.vue';
import UserPaymentClass from '@/entities/user-payment/user-payment-update.component';
import UserPaymentService from '@/entities/user-payment/user-payment.service';

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
  describe('UserPayment Management Update Component', () => {
    let wrapper: Wrapper<UserPaymentClass>;
    let comp: UserPaymentClass;
    let userPaymentServiceStub: SinonStubbedInstance<UserPaymentService>;

    beforeEach(() => {
      userPaymentServiceStub = sinon.createStubInstance<UserPaymentService>(UserPaymentService);

      wrapper = shallowMount<UserPaymentClass>(UserPaymentUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          userPaymentService: () => userPaymentServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.userPayment = entity;
        userPaymentServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userPaymentServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.userPayment = entity;
        userPaymentServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userPaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserPayment = { id: 123 };
        userPaymentServiceStub.find.resolves(foundUserPayment);
        userPaymentServiceStub.retrieve.resolves([foundUserPayment]);

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
