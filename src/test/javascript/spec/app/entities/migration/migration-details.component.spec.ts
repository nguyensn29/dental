/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import MigrationDetailComponent from '@/entities/migration/migration-details.vue';
import MigrationClass from '@/entities/migration/migration-details.component';
import MigrationService from '@/entities/migration/migration.service';
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
  describe('Migration Management Detail Component', () => {
    let wrapper: Wrapper<MigrationClass>;
    let comp: MigrationClass;
    let migrationServiceStub: SinonStubbedInstance<MigrationService>;

    beforeEach(() => {
      migrationServiceStub = sinon.createStubInstance<MigrationService>(MigrationService);

      wrapper = shallowMount<MigrationClass>(MigrationDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { migrationService: () => migrationServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMigration = { id: 123 };
        migrationServiceStub.find.resolves(foundMigration);

        // WHEN
        comp.retrieveMigration(123);
        await comp.$nextTick();

        // THEN
        expect(comp.migration).toBe(foundMigration);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundMigration = { id: 123 };
        migrationServiceStub.find.resolves(foundMigration);

        // WHEN
        comp.beforeRouteEnter({ params: { migrationId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.migration).toBe(foundMigration);
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
