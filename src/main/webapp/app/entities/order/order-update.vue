<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.order.home.createOrEditLabel"
          data-cy="OrderCreateUpdateHeading"
          v-text="$t('dentalApp.order.home.createOrEditLabel')"
        >
          Create or edit a Order
        </h2>
        <div>
          <div class="form-group" v-if="order.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="order.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.orderCode')" for="order-orderCode">Order Code</label>
            <input
              type="text"
              class="form-control"
              name="orderCode"
              id="order-orderCode"
              data-cy="orderCode"
              :class="{ valid: !$v.order.orderCode.$invalid, invalid: $v.order.orderCode.$invalid }"
              v-model="$v.order.orderCode.$model"
              required
            />
            <div v-if="$v.order.orderCode.$anyDirty && $v.order.orderCode.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.orderCode.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.transId')" for="order-transId">Trans Id</label>
            <input
              type="text"
              class="form-control"
              name="transId"
              id="order-transId"
              data-cy="transId"
              :class="{ valid: !$v.order.transId.$invalid, invalid: $v.order.transId.$invalid }"
              v-model="$v.order.transId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.paymentStatus')" for="order-paymentStatus">Payment Status</label>
            <input
              type="number"
              class="form-control"
              name="paymentStatus"
              id="order-paymentStatus"
              data-cy="paymentStatus"
              :class="{ valid: !$v.order.paymentStatus.$invalid, invalid: $v.order.paymentStatus.$invalid }"
              v-model.number="$v.order.paymentStatus.$model"
              required
            />
            <div v-if="$v.order.paymentStatus.$anyDirty && $v.order.paymentStatus.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.paymentStatus.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.paymentStatus.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.totalAmount')" for="order-totalAmount">Total Amount</label>
            <input
              type="number"
              class="form-control"
              name="totalAmount"
              id="order-totalAmount"
              data-cy="totalAmount"
              :class="{ valid: !$v.order.totalAmount.$invalid, invalid: $v.order.totalAmount.$invalid }"
              v-model.number="$v.order.totalAmount.$model"
              required
            />
            <div v-if="$v.order.totalAmount.$anyDirty && $v.order.totalAmount.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.totalAmount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.totalAmount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.totalDiscount')" for="order-totalDiscount">Total Discount</label>
            <input
              type="number"
              class="form-control"
              name="totalDiscount"
              id="order-totalDiscount"
              data-cy="totalDiscount"
              :class="{ valid: !$v.order.totalDiscount.$invalid, invalid: $v.order.totalDiscount.$invalid }"
              v-model.number="$v.order.totalDiscount.$model"
              required
            />
            <div v-if="$v.order.totalDiscount.$anyDirty && $v.order.totalDiscount.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.totalDiscount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.totalDiscount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.point')" for="order-point">Point</label>
            <input
              type="number"
              class="form-control"
              name="point"
              id="order-point"
              data-cy="point"
              :class="{ valid: !$v.order.point.$invalid, invalid: $v.order.point.$invalid }"
              v-model.number="$v.order.point.$model"
              required
            />
            <div v-if="$v.order.point.$anyDirty && $v.order.point.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.point.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.point.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.userId')" for="order-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="order-userId"
              data-cy="userId"
              :class="{ valid: !$v.order.userId.$invalid, invalid: $v.order.userId.$invalid }"
              v-model.number="$v.order.userId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.payMethod')" for="order-payMethod">Pay Method</label>
            <input
              type="number"
              class="form-control"
              name="payMethod"
              id="order-payMethod"
              data-cy="payMethod"
              :class="{ valid: !$v.order.payMethod.$invalid, invalid: $v.order.payMethod.$invalid }"
              v-model.number="$v.order.payMethod.$model"
              required
            />
            <div v-if="$v.order.payMethod.$anyDirty && $v.order.payMethod.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.payMethod.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.payMethod.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.shippingDate')" for="order-shippingDate">Shipping Date</label>
            <div class="d-flex">
              <input
                id="order-shippingDate"
                data-cy="shippingDate"
                type="datetime-local"
                class="form-control"
                name="shippingDate"
                :class="{ valid: !$v.order.shippingDate.$invalid, invalid: $v.order.shippingDate.$invalid }"
                :value="convertDateTimeFromServer($v.order.shippingDate.$model)"
                @change="updateInstantField('shippingDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.shippingStatus')" for="order-shippingStatus"
              >Shipping Status</label
            >
            <input
              type="number"
              class="form-control"
              name="shippingStatus"
              id="order-shippingStatus"
              data-cy="shippingStatus"
              :class="{ valid: !$v.order.shippingStatus.$invalid, invalid: $v.order.shippingStatus.$invalid }"
              v-model.number="$v.order.shippingStatus.$model"
              required
            />
            <div v-if="$v.order.shippingStatus.$anyDirty && $v.order.shippingStatus.$invalid">
              <small class="form-text text-danger" v-if="!$v.order.shippingStatus.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.order.shippingStatus.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.deliveryCode')" for="order-deliveryCode">Delivery Code</label>
            <input
              type="text"
              class="form-control"
              name="deliveryCode"
              id="order-deliveryCode"
              data-cy="deliveryCode"
              :class="{ valid: !$v.order.deliveryCode.$invalid, invalid: $v.order.deliveryCode.$invalid }"
              v-model="$v.order.deliveryCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.name')" for="order-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="order-name"
              data-cy="name"
              :class="{ valid: !$v.order.name.$invalid, invalid: $v.order.name.$invalid }"
              v-model="$v.order.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.phone')" for="order-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="order-phone"
              data-cy="phone"
              :class="{ valid: !$v.order.phone.$invalid, invalid: $v.order.phone.$invalid }"
              v-model="$v.order.phone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.address')" for="order-address">Address</label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="order-address"
              data-cy="address"
              :class="{ valid: !$v.order.address.$invalid, invalid: $v.order.address.$invalid }"
              v-model="$v.order.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.provincial')" for="order-provincial">Provincial</label>
            <input
              type="text"
              class="form-control"
              name="provincial"
              id="order-provincial"
              data-cy="provincial"
              :class="{ valid: !$v.order.provincial.$invalid, invalid: $v.order.provincial.$invalid }"
              v-model="$v.order.provincial.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.district')" for="order-district">District</label>
            <input
              type="text"
              class="form-control"
              name="district"
              id="order-district"
              data-cy="district"
              :class="{ valid: !$v.order.district.$invalid, invalid: $v.order.district.$invalid }"
              v-model="$v.order.district.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.shopifyId')" for="order-shopifyId">Shopify Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="order-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.order.shopifyId.$invalid, invalid: $v.order.shopifyId.$invalid }"
              v-model.number="$v.order.shopifyId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.shopifyUserId')" for="order-shopifyUserId">Shopify User Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyUserId"
              id="order-shopifyUserId"
              data-cy="shopifyUserId"
              :class="{ valid: !$v.order.shopifyUserId.$invalid, invalid: $v.order.shopifyUserId.$invalid }"
              v-model.number="$v.order.shopifyUserId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.totalWeight')" for="order-totalWeight">Total Weight</label>
            <input
              type="number"
              class="form-control"
              name="totalWeight"
              id="order-totalWeight"
              data-cy="totalWeight"
              :class="{ valid: !$v.order.totalWeight.$invalid, invalid: $v.order.totalWeight.$invalid }"
              v-model.number="$v.order.totalWeight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.fulfillmentStatus')" for="order-fulfillmentStatus"
              >Fulfillment Status</label
            >
            <input
              type="text"
              class="form-control"
              name="fulfillmentStatus"
              id="order-fulfillmentStatus"
              data-cy="fulfillmentStatus"
              :class="{ valid: !$v.order.fulfillmentStatus.$invalid, invalid: $v.order.fulfillmentStatus.$invalid }"
              v-model="$v.order.fulfillmentStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.gateway')" for="order-gateway">Gateway</label>
            <input
              type="text"
              class="form-control"
              name="gateway"
              id="order-gateway"
              data-cy="gateway"
              :class="{ valid: !$v.order.gateway.$invalid, invalid: $v.order.gateway.$invalid }"
              v-model="$v.order.gateway.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.ward')" for="order-ward">Ward</label>
            <input
              type="text"
              class="form-control"
              name="ward"
              id="order-ward"
              data-cy="ward"
              :class="{ valid: !$v.order.ward.$invalid, invalid: $v.order.ward.$invalid }"
              v-model="$v.order.ward.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.street')" for="order-street">Street</label>
            <input
              type="text"
              class="form-control"
              name="street"
              id="order-street"
              data-cy="street"
              :class="{ valid: !$v.order.street.$invalid, invalid: $v.order.street.$invalid }"
              v-model="$v.order.street.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.fulfillmentDate')" for="order-fulfillmentDate"
              >Fulfillment Date</label
            >
            <div class="d-flex">
              <input
                id="order-fulfillmentDate"
                data-cy="fulfillmentDate"
                type="datetime-local"
                class="form-control"
                name="fulfillmentDate"
                :class="{ valid: !$v.order.fulfillmentDate.$invalid, invalid: $v.order.fulfillmentDate.$invalid }"
                :value="convertDateTimeFromServer($v.order.fulfillmentDate.$model)"
                @change="updateInstantField('fulfillmentDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.cancelledAt')" for="order-cancelledAt">Cancelled At</label>
            <div class="d-flex">
              <input
                id="order-cancelledAt"
                data-cy="cancelledAt"
                type="datetime-local"
                class="form-control"
                name="cancelledAt"
                :class="{ valid: !$v.order.cancelledAt.$invalid, invalid: $v.order.cancelledAt.$invalid }"
                :value="convertDateTimeFromServer($v.order.cancelledAt.$model)"
                @change="updateInstantField('cancelledAt', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.order.cancelReason')" for="order-cancelReason">Cancel Reason</label>
            <input
              type="text"
              class="form-control"
              name="cancelReason"
              id="order-cancelReason"
              data-cy="cancelReason"
              :class="{ valid: !$v.order.cancelReason.$invalid, invalid: $v.order.cancelReason.$invalid }"
              v-model="$v.order.cancelReason.$model"
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
            :disabled="$v.order.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./order-update.component.ts"></script>
