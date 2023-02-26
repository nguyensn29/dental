<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="order">
        <h2 class="jh-entity-heading" data-cy="orderDetailsHeading">
          <span v-text="$t('dentalApp.order.detail.title')">Order</span> {{ order.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.order.orderCode')">Order Code</span>
          </dt>
          <dd>
            <span>{{ order.orderCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.transId')">Trans Id</span>
          </dt>
          <dd>
            <span>{{ order.transId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.paymentStatus')">Payment Status</span>
          </dt>
          <dd>
            <span>{{ order.paymentStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.totalAmount')">Total Amount</span>
          </dt>
          <dd>
            <span>{{ order.totalAmount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.totalDiscount')">Total Discount</span>
          </dt>
          <dd>
            <span>{{ order.totalDiscount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.point')">Point</span>
          </dt>
          <dd>
            <span>{{ order.point }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ order.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.payMethod')">Pay Method</span>
          </dt>
          <dd>
            <span>{{ order.payMethod }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.shippingDate')">Shipping Date</span>
          </dt>
          <dd>
            <span v-if="order.shippingDate">{{ $d(Date.parse(order.shippingDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.shippingStatus')">Shipping Status</span>
          </dt>
          <dd>
            <span>{{ order.shippingStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.deliveryCode')">Delivery Code</span>
          </dt>
          <dd>
            <span>{{ order.deliveryCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.name')">Name</span>
          </dt>
          <dd>
            <span>{{ order.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.phone')">Phone</span>
          </dt>
          <dd>
            <span>{{ order.phone }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.address')">Address</span>
          </dt>
          <dd>
            <span>{{ order.address }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.provincial')">Provincial</span>
          </dt>
          <dd>
            <span>{{ order.provincial }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.district')">District</span>
          </dt>
          <dd>
            <span>{{ order.district }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ order.shopifyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.shopifyUserId')">Shopify User Id</span>
          </dt>
          <dd>
            <span>{{ order.shopifyUserId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.totalWeight')">Total Weight</span>
          </dt>
          <dd>
            <span>{{ order.totalWeight }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.fulfillmentStatus')">Fulfillment Status</span>
          </dt>
          <dd>
            <span>{{ order.fulfillmentStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.gateway')">Gateway</span>
          </dt>
          <dd>
            <span>{{ order.gateway }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.ward')">Ward</span>
          </dt>
          <dd>
            <span>{{ order.ward }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.street')">Street</span>
          </dt>
          <dd>
            <span>{{ order.street }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.fulfillmentDate')">Fulfillment Date</span>
          </dt>
          <dd>
            <span v-if="order.fulfillmentDate">{{ $d(Date.parse(order.fulfillmentDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.cancelledAt')">Cancelled At</span>
          </dt>
          <dd>
            <span v-if="order.cancelledAt">{{ $d(Date.parse(order.cancelledAt), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.order.cancelReason')">Cancel Reason</span>
          </dt>
          <dd>
            <span>{{ order.cancelReason }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="order.id" :to="{ name: 'OrderEdit', params: { orderId: order.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./order-details.component.ts"></script>
