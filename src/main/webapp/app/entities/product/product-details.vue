<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="product">
        <h2 class="jh-entity-heading" data-cy="productDetailsHeading">
          <span v-text="$t('dentalApp.product.detail.title')">Product</span> {{ product.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.product.name')">Name</span>
          </dt>
          <dd>
            <span>{{ product.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.point')">Point</span>
          </dt>
          <dd>
            <span>{{ product.point }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.description')">Description</span>
          </dt>
          <dd>
            <span>{{ product.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.liked')">Liked</span>
          </dt>
          <dd>
            <span>{{ product.liked }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ product.shopifyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.vendorId')">Vendor Id</span>
          </dt>
          <dd>
            <span>{{ product.vendorId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.categoryId')">Category Id</span>
          </dt>
          <dd>
            <span>{{ product.categoryId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.isCombo')">Is Combo</span>
          </dt>
          <dd>
            <span>{{ product.isCombo }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.discount')">Discount</span>
          </dt>
          <dd>
            <span>{{ product.discount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.bought')">Bought</span>
          </dt>
          <dd>
            <span>{{ product.bought }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.product.price')">Price</span>
          </dt>
          <dd>
            <span>{{ product.price }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="product.id" :to="{ name: 'ProductEdit', params: { productId: product.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./product-details.component.ts"></script>
