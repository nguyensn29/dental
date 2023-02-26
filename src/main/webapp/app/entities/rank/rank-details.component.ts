import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRank } from '@/shared/model/rank.model';
import RankService from './rank.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class RankDetails extends Vue {
  @Inject('rankService') private rankService: () => RankService;
  @Inject('alertService') private alertService: () => AlertService;

  public rank: IRank = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rankId) {
        vm.retrieveRank(to.params.rankId);
      }
    });
  }

  public retrieveRank(rankId) {
    this.rankService()
      .find(rankId)
      .then(res => {
        this.rank = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
