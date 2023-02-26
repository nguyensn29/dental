/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ProductUserUpdateComponent from '@/entities/product-user/product-user-update.vue';
import ProductUserClass from '@/entities/product-user/product-user-update.component';
import ProductUserService from '@/entities/product-user/product-user.service';

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
  describe('ProductUser Management Update Component', () => {
    let wrapper: Wrapper<ProductUserClass>;
    let comp: ProductUserClass;
    let productUserServiceStub: SinonStubbedInstance<ProductUserService>;

    beforeEach(() => {
      productUserServiceStub = sinon.createStubInstance<ProductUserService>(ProductUserService);

      wrapper = shallowMount<ProductUserClass>(ProductUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          productUserService: () => productUserServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.productUser = entity;
        productUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.productUser = entity;
        productUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(productUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundProductUser = { id: 123 };
        productUserServiceStub.find.resolves(foundProductUser);
        productUserServiceStub.retrieve.resolves([foundProductUser]);

        // WHEN
        comp.beforeRouteEnter({ params: { productUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.productUser).toBe(foundProductUser);
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
