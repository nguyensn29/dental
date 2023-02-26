/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import VariantComponent from '@/entities/variant/variant.vue';
import VariantClass from '@/entities/variant/variant.component';
import VariantService from '@/entities/variant/variant.service';
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
  describe('Variant Management Component', () => {
    let wrapper: Wrapper<VariantClass>;
    let comp: VariantClass;
    let variantServiceStub: SinonStubbedInstance<VariantService>;

    beforeEach(() => {
      variantServiceStub = sinon.createStubInstance<VariantService>(VariantService);
      variantServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<VariantClass>(VariantComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          variantService: () => variantServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      variantServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllVariants();
      await comp.$nextTick();

      // THEN
      expect(variantServiceStub.retrieve.called).toBeTruthy();
      expect(comp.variants[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      variantServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(variantServiceStub.retrieve.callCount).toEqual(1);

      comp.removeVariant();
      await comp.$nextTick();

      // THEN
      expect(variantServiceStub.delete.called).toBeTruthy();
      expect(variantServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
