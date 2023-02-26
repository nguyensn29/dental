/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import VariantDetailComponent from '@/entities/variant/variant-details.vue';
import VariantClass from '@/entities/variant/variant-details.component';
import VariantService from '@/entities/variant/variant.service';
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
  describe('Variant Management Detail Component', () => {
    let wrapper: Wrapper<VariantClass>;
    let comp: VariantClass;
    let variantServiceStub: SinonStubbedInstance<VariantService>;

    beforeEach(() => {
      variantServiceStub = sinon.createStubInstance<VariantService>(VariantService);

      wrapper = shallowMount<VariantClass>(VariantDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { variantService: () => variantServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVariant = { id: 123 };
        variantServiceStub.find.resolves(foundVariant);

        // WHEN
        comp.retrieveVariant(123);
        await comp.$nextTick();

        // THEN
        expect(comp.variant).toBe(foundVariant);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVariant = { id: 123 };
        variantServiceStub.find.resolves(foundVariant);

        // WHEN
        comp.beforeRouteEnter({ params: { variantId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.variant).toBe(foundVariant);
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
