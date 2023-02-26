<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="cart">
        <h2 class="jh-entity-heading" data-cy="cartDetailsHeading">
          <span v-text="$t('dentalApp.cart.detail.title')">Cart</span> {{ cart.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.cart.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ cart.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.cart.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ cart.productId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.cart.variantId')">Variant Id</span>
          </dt>
          <dd>
            <span>{{ cart.variantId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.cart.productType')">Product Type</span>
          </dt>
          <dd>
            <span>{{ cart.productType }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.cart.qty')">Qty</span>
          </dt>
          <dd>
            <span>{{ cart.qty }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="cart.id" :to="{ name: 'CartEdit', params: { cartId: cart.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./cart-details.component.ts"></script>
