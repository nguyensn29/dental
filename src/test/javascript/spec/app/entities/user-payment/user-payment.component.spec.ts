/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import UserPaymentComponent from '@/entities/user-payment/user-payment.vue';
import UserPaymentClass from '@/entities/user-payment/user-payment.component';
import UserPaymentService from '@/entities/user-payment/user-payment.service';
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
  describe('UserPayment Management Component', () => {
    let wrapper: Wrapper<UserPaymentClass>;
    let comp: UserPaymentClass;
    let userPaymentServiceStub: SinonStubbedInstance<UserPaymentService>;

    beforeEach(() => {
      userPaymentServiceStub = sinon.createStubInstance<UserPaymentService>(UserPaymentService);
      userPaymentServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UserPaymentClass>(UserPaymentComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          userPaymentService: () => userPaymentServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      userPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUserPayments();
      await comp.$nextTick();

      // THEN
      expect(userPaymentServiceStub.retrieve.called).toBeTruthy();
      expect(comp.userPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      userPaymentServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(userPaymentServiceStub.retrieve.callCount).toEqual(1);

      comp.removeUserPayment();
      await comp.$nextTick();

      // THEN
      expect(userPaymentServiceStub.delete.called).toBeTruthy();
      expect(userPaymentServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
