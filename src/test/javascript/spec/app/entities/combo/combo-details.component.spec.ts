/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ComboDetailComponent from '@/entities/combo/combo-details.vue';
import ComboClass from '@/entities/combo/combo-details.component';
import ComboService from '@/entities/combo/combo.service';
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
  describe('Combo Management Detail Component', () => {
    let wrapper: Wrapper<ComboClass>;
    let comp: ComboClass;
    let comboServiceStub: SinonStubbedInstance<ComboService>;

    beforeEach(() => {
      comboServiceStub = sinon.createStubInstance<ComboService>(ComboService);

      wrapper = shallowMount<ComboClass>(ComboDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { comboService: () => comboServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCombo = { id: 123 };
        comboServiceStub.find.resolves(foundCombo);

        // WHEN
        comp.retrieveCombo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.combo).toBe(foundCombo);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCombo = { id: 123 };
        comboServiceStub.find.resolves(foundCombo);

        // WHEN
        comp.beforeRouteEnter({ params: { comboId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.combo).toBe(foundCombo);
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
