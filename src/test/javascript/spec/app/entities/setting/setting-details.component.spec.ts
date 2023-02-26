/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SettingDetailComponent from '@/entities/setting/setting-details.vue';
import SettingClass from '@/entities/setting/setting-details.component';
import SettingService from '@/entities/setting/setting.service';
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
  describe('Setting Management Detail Component', () => {
    let wrapper: Wrapper<SettingClass>;
    let comp: SettingClass;
    let settingServiceStub: SinonStubbedInstance<SettingService>;

    beforeEach(() => {
      settingServiceStub = sinon.createStubInstance<SettingService>(SettingService);

      wrapper = shallowMount<SettingClass>(SettingDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { settingService: () => settingServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSetting = { id: 123 };
        settingServiceStub.find.resolves(foundSetting);

        // WHEN
        comp.retrieveSetting(123);
        await comp.$nextTick();

        // THEN
        expect(comp.setting).toBe(foundSetting);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSetting = { id: 123 };
        settingServiceStub.find.resolves(foundSetting);

        // WHEN
        comp.beforeRouteEnter({ params: { settingId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.setting).toBe(foundSetting);
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
