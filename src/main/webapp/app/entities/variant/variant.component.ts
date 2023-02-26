import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IVariant } from '@/shared/model/variant.model';

import VariantService from './variant.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Variant extends Vue {
  @Inject('variantService') private variantService: () => VariantService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public variants: IVariant[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllVariants();
  }

  public clear(): void {
    this.retrieveAllVariants();
  }

  public retrieveAllVariants(): void {
    this.isFetching = true;
    this.variantService()
      .retrieve()
      .then(
        res => {
          this.variants = res.data;
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

  public prepareRemove(instance: IVariant): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeVariant(): void {
    this.variantService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.variant.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllVariants();
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
