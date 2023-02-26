<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="productUser">
        <h2 class="jh-entity-heading" data-cy="productUserDetailsHeading">
          <span v-text="$t('dentalApp.productUser.detail.title')">ProductUser</span> {{ productUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.productUser.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ productUser.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.productUser.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ productUser.productId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.productUser.productType')">Product Type</span>
          </dt>
          <dd>
            <span>{{ productUser.productType }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="productUser.id"
          :to="{ name: 'ProductUserEdit', params: { productUserId: productUser.id } }"
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

<script lang="ts" src="./product-user-details.component.ts"></script>
