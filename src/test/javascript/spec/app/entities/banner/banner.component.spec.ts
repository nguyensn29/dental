/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import BannerComponent from '@/entities/banner/banner.vue';
import BannerClass from '@/entities/banner/banner.component';
import BannerService from '@/entities/banner/banner.service';
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
  describe('Banner Management Component', () => {
    let wrapper: Wrapper<BannerClass>;
    let comp: BannerClass;
    let bannerServiceStub: SinonStubbedInstance<BannerService>;

    beforeEach(() => {
      bannerServiceStub = sinon.createStubInstance<BannerService>(BannerService);
      bannerServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BannerClass>(BannerComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          bannerService: () => bannerServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      bannerServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBanners();
      await comp.$nextTick();

      // THEN
      expect(bannerServiceStub.retrieve.called).toBeTruthy();
      expect(comp.banners[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      bannerServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(bannerServiceStub.retrieve.callCount).toEqual(1);

      comp.removeBanner();
      await comp.$nextTick();

      // THEN
      expect(bannerServiceStub.delete.called).toBeTruthy();
      expect(bannerServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
