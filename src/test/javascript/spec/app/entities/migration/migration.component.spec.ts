/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import MigrationComponent from '@/entities/migration/migration.vue';
import MigrationClass from '@/entities/migration/migration.component';
import MigrationService from '@/entities/migration/migration.service';
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
  describe('Migration Management Component', () => {
    let wrapper: Wrapper<MigrationClass>;
    let comp: MigrationClass;
    let migrationServiceStub: SinonStubbedInstance<MigrationService>;

    beforeEach(() => {
      migrationServiceStub = sinon.createStubInstance<MigrationService>(MigrationService);
      migrationServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MigrationClass>(MigrationComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          migrationService: () => migrationServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      migrationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMigrations();
      await comp.$nextTick();

      // THEN
      expect(migrationServiceStub.retrieve.called).toBeTruthy();
      expect(comp.migrations[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      migrationServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(migrationServiceStub.retrieve.callCount).toEqual(1);

      comp.removeMigration();
      await comp.$nextTick();

      // THEN
      expect(migrationServiceStub.delete.called).toBeTruthy();
      expect(migrationServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
