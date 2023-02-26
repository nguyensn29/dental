/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ImageComponent from '@/entities/image/image.vue';
import ImageClass from '@/entities/image/image.component';
import ImageService from '@/entities/image/image.service';
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
  describe('Image Management Component', () => {
    let wrapper: Wrapper<ImageClass>;
    let comp: ImageClass;
    let imageServiceStub: SinonStubbedInstance<ImageService>;

    beforeEach(() => {
      imageServiceStub = sinon.createStubInstance<ImageService>(ImageService);
      imageServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ImageClass>(ImageComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          imageService: () => imageServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      imageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllImages();
      await comp.$nextTick();

      // THEN
      expect(imageServiceStub.retrieve.called).toBeTruthy();
      expect(comp.images[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      imageServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(imageServiceStub.retrieve.callCount).toEqual(1);

      comp.removeImage();
      await comp.$nextTick();

      // THEN
      expect(imageServiceStub.delete.called).toBeTruthy();
      expect(imageServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
