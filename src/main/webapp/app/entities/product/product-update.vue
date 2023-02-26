<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.product.home.createOrEditLabel"
          data-cy="ProductCreateUpdateHeading"
          v-text="$t('dentalApp.product.home.createOrEditLabel')"
        >
          Create or edit a Product
        </h2>
        <div>
          <div class="form-group" v-if="product.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="product.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.name')" for="product-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="product-name"
              data-cy="name"
              :class="{ valid: !$v.product.name.$invalid, invalid: $v.product.name.$invalid }"
              v-model="$v.product.name.$model"
              required
            />
            <div v-if="$v.product.name.$anyDirty && $v.product.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.point')" for="product-point">Point</label>
            <input
              type="number"
              class="form-control"
              name="point"
              id="product-point"
              data-cy="point"
              :class="{ valid: !$v.product.point.$invalid, invalid: $v.product.point.$invalid }"
              v-model.number="$v.product.point.$model"
              required
            />
            <div v-if="$v.product.point.$anyDirty && $v.product.point.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.point.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.product.point.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.description')" for="product-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="product-description"
              data-cy="description"
              :class="{ valid: !$v.product.description.$invalid, invalid: $v.product.description.$invalid }"
              v-model="$v.product.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.liked')" for="product-liked">Liked</label>
            <input
              type="number"
              class="form-control"
              name="liked"
              id="product-liked"
              data-cy="liked"
              :class="{ valid: !$v.product.liked.$invalid, invalid: $v.product.liked.$invalid }"
              v-model.number="$v.product.liked.$model"
              required
            />
            <div v-if="$v.product.liked.$anyDirty && $v.product.liked.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.liked.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.product.liked.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.shopifyId')" for="product-shopifyId">Shopify Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="product-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.product.shopifyId.$invalid, invalid: $v.product.shopifyId.$invalid }"
              v-model.number="$v.product.shopifyId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.vendorId')" for="product-vendorId">Vendor Id</label>
            <input
              type="number"
              class="form-control"
              name="vendorId"
              id="product-vendorId"
              data-cy="vendorId"
              :class="{ valid: !$v.product.vendorId.$invalid, invalid: $v.product.vendorId.$invalid }"
              v-model.number="$v.product.vendorId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.categoryId')" for="product-categoryId">Category Id</label>
            <input
              type="number"
              class="form-control"
              name="categoryId"
              id="product-categoryId"
              data-cy="categoryId"
              :class="{ valid: !$v.product.categoryId.$invalid, invalid: $v.product.categoryId.$invalid }"
              v-model.number="$v.product.categoryId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.isCombo')" for="product-isCombo">Is Combo</label>
            <input
              type="number"
              class="form-control"
              name="isCombo"
              id="product-isCombo"
              data-cy="isCombo"
              :class="{ valid: !$v.product.isCombo.$invalid, invalid: $v.product.isCombo.$invalid }"
              v-model.number="$v.product.isCombo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.discount')" for="product-discount">Discount</label>
            <input
              type="number"
              class="form-control"
              name="discount"
              id="product-discount"
              data-cy="discount"
              :class="{ valid: !$v.product.discount.$invalid, invalid: $v.product.discount.$invalid }"
              v-model.number="$v.product.discount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.bought')" for="product-bought">Bought</label>
            <input
              type="number"
              class="form-control"
              name="bought"
              id="product-bought"
              data-cy="bought"
              :class="{ valid: !$v.product.bought.$invalid, invalid: $v.product.bought.$invalid }"
              v-model.number="$v.product.bought.$model"
              required
            />
            <div v-if="$v.product.bought.$anyDirty && $v.product.bought.$invalid">
              <small class="form-text text-danger" v-if="!$v.product.bought.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.product.bought.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.product.price')" for="product-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="product-price"
              data-cy="price"
              :class="{ valid: !$v.product.price.$invalid, invalid: $v.product.price.$invalid }"
              v-model.number="$v.product.price.$model"
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
            :disabled="$v.product.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-update.component.ts"></script>
