import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMigration } from '@/shared/model/migration.model';
import MigrationService from './migration.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class MigrationDetails extends Vue {
  @Inject('migrationService') private migrationService: () => MigrationService;
  @Inject('alertService') private alertService: () => AlertService;

  public migration: IMigration = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.migrationId) {
        vm.retrieveMigration(to.params.migrationId);
      }
    });
  }

  public retrieveMigration(migrationId) {
    this.migrationService()
      .find(migrationId)
      .then(res => {
        this.migration = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
