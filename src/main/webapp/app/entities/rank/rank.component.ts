import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRank } from '@/shared/model/rank.model';

import RankService from './rank.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Rank extends Vue {
  @Inject('rankService') private rankService: () => RankService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public ranks: IRank[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRanks();
  }

  public clear(): void {
    this.retrieveAllRanks();
  }

  public retrieveAllRanks(): void {
    this.isFetching = true;
    this.rankService()
      .retrieve()
      .then(
        res => {
          this.ranks = res.data;
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

  public prepareRemove(instance: IRank): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRank(): void {
    this.rankService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.rank.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRanks();
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
