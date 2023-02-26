/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import SettingComponent from '@/entities/setting/setting.vue';
import SettingClass from '@/entities/setting/setting.component';
import SettingService from '@/entities/setting/setting.service';
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
  describe('Setting Management Component', () => {
    let wrapper: Wrapper<SettingClass>;
    let comp: SettingClass;
    let settingServiceStub: SinonStubbedInstance<SettingService>;

    beforeEach(() => {
      settingServiceStub = sinon.createStubInstance<SettingService>(SettingService);
      settingServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SettingClass>(SettingComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          settingService: () => settingServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      settingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSettings();
      await comp.$nextTick();

      // THEN
      expect(settingServiceStub.retrieve.called).toBeTruthy();
      expect(comp.settings[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      settingServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(settingServiceStub.retrieve.callCount).toEqual(1);

      comp.removeSetting();
      await comp.$nextTick();

      // THEN
      expect(settingServiceStub.delete.called).toBeTruthy();
      expect(settingServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
