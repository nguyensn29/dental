<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.image.home.createOrEditLabel"
          data-cy="ImageCreateUpdateHeading"
          v-text="$t('dentalApp.image.home.createOrEditLabel')"
        >
          Create or edit a Image
        </h2>
        <div>
          <div class="form-group" v-if="image.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="image.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.src')" for="image-src">Src</label>
            <input
              type="text"
              class="form-control"
              name="src"
              id="image-src"
              data-cy="src"
              :class="{ valid: !$v.image.src.$invalid, invalid: $v.image.src.$invalid }"
              v-model="$v.image.src.$model"
              required
            />
            <div v-if="$v.image.src.$anyDirty && $v.image.src.$invalid">
              <small class="form-text text-danger" v-if="!$v.image.src.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.shopifyId')" for="image-shopifyId">Shopify Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="image-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.image.shopifyId.$invalid, invalid: $v.image.shopifyId.$invalid }"
              v-model.number="$v.image.shopifyId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.imgPublicId')" for="image-imgPublicId">Img Public Id</label>
            <input
              type="text"
              class="form-control"
              name="imgPublicId"
              id="image-imgPublicId"
              data-cy="imgPublicId"
              :class="{ valid: !$v.image.imgPublicId.$invalid, invalid: $v.image.imgPublicId.$invalid }"
              v-model="$v.image.imgPublicId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.isAvatar')" for="image-isAvatar">Is Avatar</label>
            <input
              type="number"
              class="form-control"
              name="isAvatar"
              id="image-isAvatar"
              data-cy="isAvatar"
              :class="{ valid: !$v.image.isAvatar.$invalid, invalid: $v.image.isAvatar.$invalid }"
              v-model.number="$v.image.isAvatar.$model"
              required
            />
            <div v-if="$v.image.isAvatar.$anyDirty && $v.image.isAvatar.$invalid">
              <small class="form-text text-danger" v-if="!$v.image.isAvatar.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.image.isAvatar.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.comboId')" for="image-comboId">Combo Id</label>
            <input
              type="number"
              class="form-control"
              name="comboId"
              id="image-comboId"
              data-cy="comboId"
              :class="{ valid: !$v.image.comboId.$invalid, invalid: $v.image.comboId.$invalid }"
              v-model.number="$v.image.comboId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.image.productId')" for="image-productId">Product Id</label>
            <input
              type="number"
              class="form-control"
              name="productId"
              id="image-productId"
              data-cy="productId"
              :class="{ valid: !$v.image.productId.$invalid, invalid: $v.image.productId.$invalid }"
              v-model.number="$v.image.productId.$model"
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
            :disabled="$v.image.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./image-update.component.ts"></script>
