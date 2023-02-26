import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICollection } from '@/shared/model/collection.model';
import CollectionService from './collection.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CollectionDetails extends Vue {
  @Inject('collectionService') private collectionService: () => CollectionService;
  @Inject('alertService') private alertService: () => AlertService;

  public collection: ICollection = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.collectionId) {
        vm.retrieveCollection(to.params.collectionId);
      }
    });
  }

  public retrieveCollection(collectionId) {
    this.collectionService()
      .find(collectionId)
      .then(res => {
        this.collection = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
