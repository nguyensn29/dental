/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import RankComponent from '@/entities/rank/rank.vue';
import RankClass from '@/entities/rank/rank.component';
import RankService from '@/entities/rank/rank.service';
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
  describe('Rank Management Component', () => {
    let wrapper: Wrapper<RankClass>;
    let comp: RankClass;
    let rankServiceStub: SinonStubbedInstance<RankService>;

    beforeEach(() => {
      rankServiceStub = sinon.createStubInstance<RankService>(RankService);
      rankServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RankClass>(RankComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          rankService: () => rankServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      rankServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRanks();
      await comp.$nextTick();

      // THEN
      expect(rankServiceStub.retrieve.called).toBeTruthy();
      expect(comp.ranks[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      rankServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(rankServiceStub.retrieve.callCount).toEqual(1);

      comp.removeRank();
      await comp.$nextTick();

      // THEN
      expect(rankServiceStub.delete.called).toBeTruthy();
      expect(rankServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
