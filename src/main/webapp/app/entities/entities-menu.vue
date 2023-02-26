<template>
  <div>
    <b-dropdown-item to="/banner">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.banner')">Banner</span>
    </b-dropdown-item>
    <b-dropdown-item to="/blog">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.blog')">Blog</span>
    </b-dropdown-item>
    <b-dropdown-item to="/cart">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.cart')">Cart</span>
    </b-dropdown-item>
    <b-dropdown-item to="/category">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.category')">Category</span>
    </b-dropdown-item>
    <b-dropdown-item to="/collection">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.collection')">Collection</span>
    </b-dropdown-item>
    <b-dropdown-item to="/product">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.product')">Product</span>
    </b-dropdown-item>
    <b-dropdown-item to="/collection-product">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.collectionProduct')">Collection Product</span>
    </b-dropdown-item>
    <b-dropdown-item to="/combo">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.combo')">Combo</span>
    </b-dropdown-item>
    <b-dropdown-item to="/image">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.image')">Image</span>
    </b-dropdown-item>
    <b-dropdown-item to="/message">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.message')">Message</span>
    </b-dropdown-item>
    <b-dropdown-item to="/migration">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.migration')">Migration</span>
    </b-dropdown-item>
    <b-dropdown-item to="/order">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.order')">Order</span>
    </b-dropdown-item>
    <b-dropdown-item to="/order-details">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.orderDetails')">Order Details</span>
    </b-dropdown-item>
    <b-dropdown-item to="/product-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.productUser')">Product User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/rank">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.rank')">Rank</span>
    </b-dropdown-item>
    <b-dropdown-item to="/setting">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.setting')">Setting</span>
    </b-dropdown-item>
    <b-dropdown-item to="/user-payment">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.userPayment')">User Payment</span>
    </b-dropdown-item>
    <b-dropdown-item to="/variant">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.variant')">Variant</span>
    </b-dropdown-item>
    <b-dropdown-item to="/vendor">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.vendor')">Vendor</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
