import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICombo } from '@/shared/model/combo.model';
import ComboService from './combo.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ComboDetails extends Vue {
  @Inject('comboService') private comboService: () => ComboService;
  @Inject('alertService') private alertService: () => AlertService;

  public combo: ICombo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.comboId) {
        vm.retrieveCombo(to.params.comboId);
      }
    });
  }

  public retrieveCombo(comboId) {
    this.comboService()
      .find(comboId)
      .then(res => {
        this.combo = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
