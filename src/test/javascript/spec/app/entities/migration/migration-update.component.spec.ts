/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import MigrationUpdateComponent from '@/entities/migration/migration-update.vue';
import MigrationClass from '@/entities/migration/migration-update.component';
import MigrationService from '@/entities/migration/migration.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Migration Management Update Component', () => {
    let wrapper: Wrapper<MigrationClass>;
    let comp: MigrationClass;
    let migrationServiceStub: SinonStubbedInstance<MigrationService>;

    beforeEach(() => {
      migrationServiceStub = sinon.createStubInstance<MigrationService>(MigrationService);

      wrapper = shallowMount<MigrationClass>(MigrationUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          migrationService: () => migrationServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.migration = entity;
        migrationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(migrationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.migration = entity;
        migrationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(migrationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundMigration = { id: 123 };
        migrationServiceStub.find.resolves(foundMigration);
        migrationServiceStub.retrieve.resolves([foundMigration]);

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
