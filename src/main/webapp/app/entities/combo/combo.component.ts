import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICombo } from '@/shared/model/combo.model';

import ComboService from './combo.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Combo extends Vue {
  @Inject('comboService') private comboService: () => ComboService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public combos: ICombo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCombos();
  }

  public clear(): void {
    this.retrieveAllCombos();
  }

  public retrieveAllCombos(): void {
    this.isFetching = true;
    this.comboService()
      .retrieve()
      .then(
        res => {
          this.combos = res.data;
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

  public prepareRemove(instance: ICombo): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCombo(): void {
    this.comboService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.combo.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCombos();
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
