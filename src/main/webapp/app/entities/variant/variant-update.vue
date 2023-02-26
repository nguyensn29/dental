<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.variant.home.createOrEditLabel"
          data-cy="VariantCreateUpdateHeading"
          v-text="$t('dentalApp.variant.home.createOrEditLabel')"
        >
          Create or edit a Variant
        </h2>
        <div>
          <div class="form-group" v-if="variant.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="variant.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.productId')" for="variant-productId">Product Id</label>
            <input
              type="number"
              class="form-control"
              name="productId"
              id="variant-productId"
              data-cy="productId"
              :class="{ valid: !$v.variant.productId.$invalid, invalid: $v.variant.productId.$invalid }"
              v-model.number="$v.variant.productId.$model"
              required
            />
            <div v-if="$v.variant.productId.$anyDirty && $v.variant.productId.$invalid">
              <small class="form-text text-danger" v-if="!$v.variant.productId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.variant.productId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.shopifyId')" for="variant-shopifyId">Shopify Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="variant-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.variant.shopifyId.$invalid, invalid: $v.variant.shopifyId.$invalid }"
              v-model.number="$v.variant.shopifyId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.title')" for="variant-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="variant-title"
              data-cy="title"
              :class="{ valid: !$v.variant.title.$invalid, invalid: $v.variant.title.$invalid }"
              v-model="$v.variant.title.$model"
              required
            />
            <div v-if="$v.variant.title.$anyDirty && $v.variant.title.$invalid">
              <small class="form-text text-danger" v-if="!$v.variant.title.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.price')" for="variant-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="variant-price"
              data-cy="price"
              :class="{ valid: !$v.variant.price.$invalid, invalid: $v.variant.price.$invalid }"
              v-model.number="$v.variant.price.$model"
              required
            />
            <div v-if="$v.variant.price.$anyDirty && $v.variant.price.$invalid">
              <small class="form-text text-danger" v-if="!$v.variant.price.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.variant.price.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.discount')" for="variant-discount">Discount</label>
            <input
              type="number"
              class="form-control"
              name="discount"
              id="variant-discount"
              data-cy="discount"
              :class="{ valid: !$v.variant.discount.$invalid, invalid: $v.variant.discount.$invalid }"
              v-model.number="$v.variant.discount.$model"
              required
            />
            <div v-if="$v.variant.discount.$anyDirty && $v.variant.discount.$invalid">
              <small class="form-text text-danger" v-if="!$v.variant.discount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.variant.discount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.option1')" for="variant-option1">Option 1</label>
            <input
              type="text"
              class="form-control"
              name="option1"
              id="variant-option1"
              data-cy="option1"
              :class="{ valid: !$v.variant.option1.$invalid, invalid: $v.variant.option1.$invalid }"
              v-model="$v.variant.option1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.option2')" for="variant-option2">Option 2</label>
            <input
              type="text"
              class="form-control"
              name="option2"
              id="variant-option2"
              data-cy="option2"
              :class="{ valid: !$v.variant.option2.$invalid, invalid: $v.variant.option2.$invalid }"
              v-model="$v.variant.option2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.option3')" for="variant-option3">Option 3</label>
            <input
              type="text"
              class="form-control"
              name="option3"
              id="variant-option3"
              data-cy="option3"
              :class="{ valid: !$v.variant.option3.$invalid, invalid: $v.variant.option3.$invalid }"
              v-model="$v.variant.option3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.imageId')" for="variant-imageId">Image Id</label>
            <input
              type="number"
              class="form-control"
              name="imageId"
              id="variant-imageId"
              data-cy="imageId"
              :class="{ valid: !$v.variant.imageId.$invalid, invalid: $v.variant.imageId.$invalid }"
              v-model.number="$v.variant.imageId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.weight')" for="variant-weight">Weight</label>
            <input
              type="number"
              class="form-control"
              name="weight"
              id="variant-weight"
              data-cy="weight"
              :class="{ valid: !$v.variant.weight.$invalid, invalid: $v.variant.weight.$invalid }"
              v-model.number="$v.variant.weight.$model"
              required
            />
            <div v-if="$v.variant.weight.$anyDirty && $v.variant.weight.$invalid">
              <small class="form-text text-danger" v-if="!$v.variant.weight.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.variant.weight.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.variant.compareAtPrice')" for="variant-compareAtPrice"
              >Compare At Price</label
            >
            <input
              type="number"
              class="form-control"
              name="compareAtPrice"
              id="variant-compareAtPrice"
              data-cy="compareAtPrice"
              :class="{ valid: !$v.variant.compareAtPrice.$invalid, invalid: $v.variant.compareAtPrice.$invalid }"
              v-model.number="$v.variant.compareAtPrice.$model"
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
            :disabled="$v.variant.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./variant-update.component.ts"></script>
