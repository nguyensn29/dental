/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ProductUserDetailComponent from '@/entities/product-user/product-user-details.vue';
import ProductUserClass from '@/entities/product-user/product-user-details.component';
import ProductUserService from '@/entities/product-user/product-user.service';
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
  describe('ProductUser Management Detail Component', () => {
    let wrapper: Wrapper<ProductUserClass>;
    let comp: ProductUserClass;
    let productUserServiceStub: SinonStubbedInstance<ProductUserService>;

    beforeEach(() => {
      productUserServiceStub = sinon.createStubInstance<ProductUserService>(ProductUserService);

      wrapper = shallowMount<ProductUserClass>(ProductUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { productUserService: () => productUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProductUser = { id: 123 };
        productUserServiceStub.find.resolves(foundProductUser);

        // WHEN
        comp.retrieveProductUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.productUser).toBe(foundProductUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundProductUser = { id: 123 };
        productUserServiceStub.find.resolves(foundProductUser);

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
