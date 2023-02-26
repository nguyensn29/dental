<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.setting.home.createOrEditLabel"
          data-cy="SettingCreateUpdateHeading"
          v-text="$t('dentalApp.setting.home.createOrEditLabel')"
        >
          Create or edit a Setting
        </h2>
        <div>
          <div class="form-group" v-if="setting.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="setting.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.name')" for="setting-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="setting-name"
              data-cy="name"
              :class="{ valid: !$v.setting.name.$invalid, invalid: $v.setting.name.$invalid }"
              v-model="$v.setting.name.$model"
              required
            />
            <div v-if="$v.setting.name.$anyDirty && $v.setting.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.setting.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.keyName')" for="setting-keyName">Key Name</label>
            <input
              type="text"
              class="form-control"
              name="keyName"
              id="setting-keyName"
              data-cy="keyName"
              :class="{ valid: !$v.setting.keyName.$invalid, invalid: $v.setting.keyName.$invalid }"
              v-model="$v.setting.keyName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.value')" for="setting-value">Value</label>
            <input
              type="text"
              class="form-control"
              name="value"
              id="setting-value"
              data-cy="value"
              :class="{ valid: !$v.setting.value.$invalid, invalid: $v.setting.value.$invalid }"
              v-model="$v.setting.value.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.rule')" for="setting-rule">Rule</label>
            <input
              type="text"
              class="form-control"
              name="rule"
              id="setting-rule"
              data-cy="rule"
              :class="{ valid: !$v.setting.rule.$invalid, invalid: $v.setting.rule.$invalid }"
              v-model="$v.setting.rule.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.isNumber')" for="setting-isNumber">Is Number</label>
            <input
              type="number"
              class="form-control"
              name="isNumber"
              id="setting-isNumber"
              data-cy="isNumber"
              :class="{ valid: !$v.setting.isNumber.$invalid, invalid: $v.setting.isNumber.$invalid }"
              v-model.number="$v.setting.isNumber.$model"
              required
            />
            <div v-if="$v.setting.isNumber.$anyDirty && $v.setting.isNumber.$invalid">
              <small class="form-text text-danger" v-if="!$v.setting.isNumber.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.setting.isNumber.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.setting.isObject')" for="setting-isObject">Is Object</label>
            <input
              type="number"
              class="form-control"
              name="isObject"
              id="setting-isObject"
              data-cy="isObject"
              :class="{ valid: !$v.setting.isObject.$invalid, invalid: $v.setting.isObject.$invalid }"
              v-model.number="$v.setting.isObject.$model"
              required
            />
            <div v-if="$v.setting.isObject.$anyDirty && $v.setting.isObject.$invalid">
              <small class="form-text text-danger" v-if="!$v.setting.isObject.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.setting.isObject.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.setting.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./setting-update.component.ts"></script>
