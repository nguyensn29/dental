import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICollection } from '@/shared/model/collection.model';

import CollectionService from './collection.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Collection extends Vue {
  @Inject('collectionService') private collectionService: () => CollectionService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public collections: ICollection[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCollections();
  }

  public clear(): void {
    this.retrieveAllCollections();
  }

  public retrieveAllCollections(): void {
    this.isFetching = true;
    this.collectionService()
      .retrieve()
      .then(
        res => {
          this.collections = res.data;
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

  public prepareRemove(instance: ICollection): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCollection(): void {
    this.collectionService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.collection.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCollections();
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
