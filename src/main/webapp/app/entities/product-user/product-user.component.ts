import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProductUser } from '@/shared/model/product-user.model';

import ProductUserService from './product-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ProductUser extends Vue {
  @Inject('productUserService') private productUserService: () => ProductUserService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public productUsers: IProductUser[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProductUsers();
  }

  public clear(): void {
    this.retrieveAllProductUsers();
  }

  public retrieveAllProductUsers(): void {
    this.isFetching = true;
    this.productUserService()
      .retrieve()
      .then(
        res => {
          this.productUsers = res.data;
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

  public prepareRemove(instance: IProductUser): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProductUser(): void {
    this.productUserService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.productUser.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProductUsers();
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
