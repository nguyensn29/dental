import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISetting } from '@/shared/model/setting.model';
import SettingService from './setting.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class SettingDetails extends Vue {
  @Inject('settingService') private settingService: () => SettingService;
  @Inject('alertService') private alertService: () => AlertService;

  public setting: ISetting = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.settingId) {
        vm.retrieveSetting(to.params.settingId);
      }
    });
  }

  public retrieveSetting(settingId) {
    this.settingService()
      .find(settingId)
      .then(res => {
        this.setting = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
