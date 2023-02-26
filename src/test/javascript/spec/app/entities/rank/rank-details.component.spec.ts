/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RankDetailComponent from '@/entities/rank/rank-details.vue';
import RankClass from '@/entities/rank/rank-details.component';
import RankService from '@/entities/rank/rank.service';
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
  describe('Rank Management Detail Component', () => {
    let wrapper: Wrapper<RankClass>;
    let comp: RankClass;
    let rankServiceStub: SinonStubbedInstance<RankService>;

    beforeEach(() => {
      rankServiceStub = sinon.createStubInstance<RankService>(RankService);

      wrapper = shallowMount<RankClass>(RankDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { rankService: () => rankServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRank = { id: 123 };
        rankServiceStub.find.resolves(foundRank);

        // WHEN
        comp.retrieveRank(123);
        await comp.$nextTick();

        // THEN
        expect(comp.rank).toBe(foundRank);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRank = { id: 123 };
        rankServiceStub.find.resolves(foundRank);

        // WHEN
        comp.beforeRouteEnter({ params: { rankId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.rank).toBe(foundRank);
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
