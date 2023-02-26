<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.orderDetails.home.createOrEditLabel"
          data-cy="OrderDetailsCreateUpdateHeading"
          v-text="$t('dentalApp.orderDetails.home.createOrEditLabel')"
        >
          Create or edit a OrderDetails
        </h2>
        <div>
          <div class="form-group" v-if="orderDetails.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="orderDetails.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.orderId')" for="order-details-orderId">Order Id</label>
            <input
              type="number"
              class="form-control"
              name="orderId"
              id="order-details-orderId"
              data-cy="orderId"
              :class="{ valid: !$v.orderDetails.orderId.$invalid, invalid: $v.orderDetails.orderId.$invalid }"
              v-model.number="$v.orderDetails.orderId.$model"
              required
            />
            <div v-if="$v.orderDetails.orderId.$anyDirty && $v.orderDetails.orderId.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.orderId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.orderId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.productId')" for="order-details-productId"
              >Product Id</label
            >
            <input
              type="number"
              class="form-control"
              name="productId"
              id="order-details-productId"
              data-cy="productId"
              :class="{ valid: !$v.orderDetails.productId.$invalid, invalid: $v.orderDetails.productId.$invalid }"
              v-model.number="$v.orderDetails.productId.$model"
              required
            />
            <div v-if="$v.orderDetails.productId.$anyDirty && $v.orderDetails.productId.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.productId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.productId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.productType')" for="order-details-productType"
              >Product Type</label
            >
            <input
              type="number"
              class="form-control"
              name="productType"
              id="order-details-productType"
              data-cy="productType"
              :class="{ valid: !$v.orderDetails.productType.$invalid, invalid: $v.orderDetails.productType.$invalid }"
              v-model.number="$v.orderDetails.productType.$model"
              required
            />
            <div v-if="$v.orderDetails.productType.$anyDirty && $v.orderDetails.productType.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.productType.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.productType.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.avatar')" for="order-details-avatar">Avatar</label>
            <input
              type="text"
              class="form-control"
              name="avatar"
              id="order-details-avatar"
              data-cy="avatar"
              :class="{ valid: !$v.orderDetails.avatar.$invalid, invalid: $v.orderDetails.avatar.$invalid }"
              v-model="$v.orderDetails.avatar.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.name')" for="order-details-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="order-details-name"
              data-cy="name"
              :class="{ valid: !$v.orderDetails.name.$invalid, invalid: $v.orderDetails.name.$invalid }"
              v-model="$v.orderDetails.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.price')" for="order-details-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="order-details-price"
              data-cy="price"
              :class="{ valid: !$v.orderDetails.price.$invalid, invalid: $v.orderDetails.price.$invalid }"
              v-model.number="$v.orderDetails.price.$model"
              required
            />
            <div v-if="$v.orderDetails.price.$anyDirty && $v.orderDetails.price.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.price.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.price.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.point')" for="order-details-point">Point</label>
            <input
              type="number"
              class="form-control"
              name="point"
              id="order-details-point"
              data-cy="point"
              :class="{ valid: !$v.orderDetails.point.$invalid, invalid: $v.orderDetails.point.$invalid }"
              v-model.number="$v.orderDetails.point.$model"
              required
            />
            <div v-if="$v.orderDetails.point.$anyDirty && $v.orderDetails.point.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.point.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.point.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.qty')" for="order-details-qty">Qty</label>
            <input
              type="number"
              class="form-control"
              name="qty"
              id="order-details-qty"
              data-cy="qty"
              :class="{ valid: !$v.orderDetails.qty.$invalid, invalid: $v.orderDetails.qty.$invalid }"
              v-model.number="$v.orderDetails.qty.$model"
              required
            />
            <div v-if="$v.orderDetails.qty.$anyDirty && $v.orderDetails.qty.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.qty.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.qty.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.discount')" for="order-details-discount">Discount</label>
            <input
              type="number"
              class="form-control"
              name="discount"
              id="order-details-discount"
              data-cy="discount"
              :class="{ valid: !$v.orderDetails.discount.$invalid, invalid: $v.orderDetails.discount.$invalid }"
              v-model.number="$v.orderDetails.discount.$model"
              required
            />
            <div v-if="$v.orderDetails.discount.$anyDirty && $v.orderDetails.discount.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.discount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.discount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.subtotalAmount')" for="order-details-subtotalAmount"
              >Subtotal Amount</label
            >
            <input
              type="number"
              class="form-control"
              name="subtotalAmount"
              id="order-details-subtotalAmount"
              data-cy="subtotalAmount"
              :class="{ valid: !$v.orderDetails.subtotalAmount.$invalid, invalid: $v.orderDetails.subtotalAmount.$invalid }"
              v-model.number="$v.orderDetails.subtotalAmount.$model"
              required
            />
            <div v-if="$v.orderDetails.subtotalAmount.$anyDirty && $v.orderDetails.subtotalAmount.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.orderDetails.subtotalAmount.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.subtotalAmount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.totalAmount')" for="order-details-totalAmount"
              >Total Amount</label
            >
            <input
              type="number"
              class="form-control"
              name="totalAmount"
              id="order-details-totalAmount"
              data-cy="totalAmount"
              :class="{ valid: !$v.orderDetails.totalAmount.$invalid, invalid: $v.orderDetails.totalAmount.$invalid }"
              v-model.number="$v.orderDetails.totalAmount.$model"
              required
            />
            <div v-if="$v.orderDetails.totalAmount.$anyDirty && $v.orderDetails.totalAmount.$invalid">
              <small class="form-text text-danger" v-if="!$v.orderDetails.totalAmount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.orderDetails.totalAmount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.variantId')" for="order-details-variantId"
              >Variant Id</label
            >
            <input
              type="number"
              class="form-control"
              name="variantId"
              id="order-details-variantId"
              data-cy="variantId"
              :class="{ valid: !$v.orderDetails.variantId.$invalid, invalid: $v.orderDetails.variantId.$invalid }"
              v-model.number="$v.orderDetails.variantId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.shopifyVariantId')" for="order-details-shopifyVariantId"
              >Shopify Variant Id</label
            >
            <input
              type="number"
              class="form-control"
              name="shopifyVariantId"
              id="order-details-shopifyVariantId"
              data-cy="shopifyVariantId"
              :class="{ valid: !$v.orderDetails.shopifyVariantId.$invalid, invalid: $v.orderDetails.shopifyVariantId.$invalid }"
              v-model.number="$v.orderDetails.shopifyVariantId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.shopifyProductId')" for="order-details-shopifyProductId"
              >Shopify Product Id</label
            >
            <input
              type="number"
              class="form-control"
              name="shopifyProductId"
              id="order-details-shopifyProductId"
              data-cy="shopifyProductId"
              :class="{ valid: !$v.orderDetails.shopifyProductId.$invalid, invalid: $v.orderDetails.shopifyProductId.$invalid }"
              v-model.number="$v.orderDetails.shopifyProductId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.shopifyOrderId')" for="order-details-shopifyOrderId"
              >Shopify Order Id</label
            >
            <input
              type="number"
              class="form-control"
              name="shopifyOrderId"
              id="order-details-shopifyOrderId"
              data-cy="shopifyOrderId"
              :class="{ valid: !$v.orderDetails.shopifyOrderId.$invalid, invalid: $v.orderDetails.shopifyOrderId.$invalid }"
              v-model.number="$v.orderDetails.shopifyOrderId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.orderDetails.shopifyId')" for="order-details-shopifyId"
              >Shopify Id</label
            >
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="order-details-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.orderDetails.shopifyId.$invalid, invalid: $v.orderDetails.shopifyId.$invalid }"
              v-model.number="$v.orderDetails.shopifyId.$model"
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
            :disabled="$v.orderDetails.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./order-details-update.component.ts"></script>
