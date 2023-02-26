<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="variant">
        <h2 class="jh-entity-heading" data-cy="variantDetailsHeading">
          <span v-text="$t('dentalApp.variant.detail.title')">Variant</span> {{ variant.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.variant.productId')">Product Id</span>
          </dt>
          <dd>
            <span>{{ variant.productId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.shopifyId')">Shopify Id</span>
          </dt>
          <dd>
            <span>{{ variant.shopifyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.title')">Title</span>
          </dt>
          <dd>
            <span>{{ variant.title }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.price')">Price</span>
          </dt>
          <dd>
            <span>{{ variant.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.discount')">Discount</span>
          </dt>
          <dd>
            <span>{{ variant.discount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.option1')">Option 1</span>
          </dt>
          <dd>
            <span>{{ variant.option1 }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.option2')">Option 2</span>
          </dt>
          <dd>
            <span>{{ variant.option2 }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.option3')">Option 3</span>
          </dt>
          <dd>
            <span>{{ variant.option3 }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.imageId')">Image Id</span>
          </dt>
          <dd>
            <span>{{ variant.imageId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.weight')">Weight</span>
          </dt>
          <dd>
            <span>{{ variant.weight }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.variant.compareAtPrice')">Compare At Price</span>
          </dt>
          <dd>
            <span>{{ variant.compareAtPrice }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="variant.id" :to="{ name: 'VariantEdit', params: { variantId: variant.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./variant-details.component.ts"></script>
