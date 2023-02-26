<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="orderDetails">
        <h2 class="jh-entity-heading" data-cy="orderDetailsDetailsHeading">
          <span v-text="$t('dentalApp.orderDetails.detail.title')">OrderDetails</span> {{ orderDetails.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.orderDetails.orderId')">Order Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.orderId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.productId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.productType')">Product Type</span>
          </dt>
          <dd>
            <span>{{ orderDetails.productType }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.avatar')">Avatar</span>
          </dt>
          <dd>
            <span>{{ orderDetails.avatar }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.name')">Name</span>
          </dt>
          <dd>
            <span>{{ orderDetails.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.price')">Price</span>
          </dt>
          <dd>
            <span>{{ orderDetails.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.point')">Point</span>
          </dt>
          <dd>
            <span>{{ orderDetails.point }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.qty')">Qty</span>
          </dt>
          <dd>
            <span>{{ orderDetails.qty }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.discount')">Discount</span>
          </dt>
          <dd>
            <span>{{ orderDetails.discount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.subtotalAmount')">Subtotal Amount</span>
          </dt>
          <dd>
            <span>{{ orderDetails.subtotalAmount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.totalAmount')">Total Amount</span>
          </dt>
          <dd>
            <span>{{ orderDetails.totalAmount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.variantId')">Variant Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.variantId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.shopifyVariantId')">Shopify Variant Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.shopifyVariantId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.shopifyProductId')">Shopify Product Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.shopifyProductId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.shopifyOrderId')">Shopify Order Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.shopifyOrderId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.orderDetails.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ orderDetails.shopifyId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="orderDetails.id"
          :to="{ name: 'OrderDetailsEdit', params: { orderDetailsId: orderDetails.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./order-details-details.component.ts"></script>
