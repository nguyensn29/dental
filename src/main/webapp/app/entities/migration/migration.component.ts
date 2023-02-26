import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IMigration } from '@/shared/model/migration.model';

import MigrationService from './migration.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Migration extends Vue {
  @Inject('migrationService') private migrationService: () => MigrationService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public migrations: IMigration[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllMigrations();
  }

  public clear(): void {
    this.retrieveAllMigrations();
  }

  public retrieveAllMigrations(): void {
    this.isFetching = true;
    this.migrationService()
      .retrieve()
      .then(
        res => {
          this.migrations = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IMigration): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeMigration(): void {
    this.migrationService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.migration.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllMigrations();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
