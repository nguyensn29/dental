<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.banner.home.createOrEditLabel"
          data-cy="BannerCreateUpdateHeading"
          v-text="$t('dentalApp.banner.home.createOrEditLabel')"
        >
          Create or edit a Banner
        </h2>
        <div>
          <div class="form-group" v-if="banner.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="banner.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.banner.name')" for="banner-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="banner-name"
              data-cy="name"
              :class="{ valid: !$v.banner.name.$invalid, invalid: $v.banner.name.$invalid }"
              v-model="$v.banner.name.$model"
              required
            />
            <div v-if="$v.banner.name.$anyDirty && $v.banner.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.banner.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.banner.src')" for="banner-src">Src</label>
            <input
              type="text"
              class="form-control"
              name="src"
              id="banner-src"
              data-cy="src"
              :class="{ valid: !$v.banner.src.$invalid, invalid: $v.banner.src.$invalid }"
              v-model="$v.banner.src.$model"
              required
            />
            <div v-if="$v.banner.src.$anyDirty && $v.banner.src.$invalid">
              <small class="form-text text-danger" v-if="!$v.banner.src.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.banner.isShow')" for="banner-isShow">Is Show</label>
            <input
              type="number"
              class="form-control"
              name="isShow"
              id="banner-isShow"
              data-cy="isShow"
              :class="{ valid: !$v.banner.isShow.$invalid, invalid: $v.banner.isShow.$invalid }"
              v-model.number="$v.banner.isShow.$model"
              required
            />
            <div v-if="$v.banner.isShow.$anyDirty && $v.banner.isShow.$invalid">
              <small class="form-text text-danger" v-if="!$v.banner.isShow.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.banner.isShow.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.banner.imgPublicId')" for="banner-imgPublicId">Img Public Id</label>
            <input
              type="text"
              class="form-control"
              name="imgPublicId"
              id="banner-imgPublicId"
              data-cy="imgPublicId"
              :class="{ valid: !$v.banner.imgPublicId.$invalid, invalid: $v.banner.imgPublicId.$invalid }"
              v-model="$v.banner.imgPublicId.$model"
            />
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
            :disabled="$v.banner.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./banner-update.component.ts"></script>
