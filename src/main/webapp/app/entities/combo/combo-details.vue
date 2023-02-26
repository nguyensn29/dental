<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="combo">
        <h2 class="jh-entity-heading" data-cy="comboDetailsHeading">
          <span v-text="$t('dentalApp.combo.detail.title')">Combo</span> {{ combo.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.combo.name')">Name</span>
          </dt>
          <dd>
            <span>{{ combo.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.price')">Price</span>
          </dt>
          <dd>
            <span>{{ combo.price }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.discount')">Discount</span>
          </dt>
          <dd>
            <span>{{ combo.discount }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.weight')">Weight</span>
          </dt>
          <dd>
            <span>{{ combo.weight }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.point')">Point</span>
          </dt>
          <dd>
            <span>{{ combo.point }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.description')">Description</span>
          </dt>
          <dd>
            <span>{{ combo.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.combo.liked')">Liked</span>
          </dt>
          <dd>
            <span>{{ combo.liked }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="combo.id" :to="{ name: 'ComboEdit', params: { comboId: combo.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./combo-details.component.ts"></script>
