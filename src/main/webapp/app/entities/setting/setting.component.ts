import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISetting } from '@/shared/model/setting.model';

import SettingService from './setting.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Setting extends Vue {
  @Inject('settingService') private settingService: () => SettingService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public settings: ISetting[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSettings();
  }

  public clear(): void {
    this.retrieveAllSettings();
  }

  public retrieveAllSettings(): void {
    this.isFetching = true;
    this.settingService()
      .retrieve()
      .then(
        res => {
          this.settings = res.data;
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

  public prepareRemove(instance: ISetting): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSetting(): void {
    this.settingService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.setting.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSettings();
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
