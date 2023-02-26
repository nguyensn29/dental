<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="userPayment">
        <h2 class="jh-entity-heading" data-cy="userPaymentDetailsHeading">
          <span v-text="$t('dentalApp.userPayment.detail.title')">UserPayment</span> {{ userPayment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.userPayment.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ userPayment.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.name')">Name</span>
          </dt>
          <dd>
            <span>{{ userPayment.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.phone')">Phone</span>
          </dt>
          <dd>
            <span>{{ userPayment.phone }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.adress')">Adress</span>
          </dt>
          <dd>
            <span>{{ userPayment.adress }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.provincial')">Provincial</span>
          </dt>
          <dd>
            <span>{{ userPayment.provincial }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.district')">District</span>
          </dt>
          <dd>
            <span>{{ userPayment.district }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.ward')">Ward</span>
          </dt>
          <dd>
            <span>{{ userPayment.ward }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.email')">Email</span>
          </dt>
          <dd>
            <span>{{ userPayment.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.userPayment.street')">Street</span>
          </dt>
          <dd>
            <span>{{ userPayment.street }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="userPayment.id"
          :to="{ name: 'UserPaymentEdit', params: { userPaymentId: userPayment.id } }"
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

<script lang="ts" src="./user-payment-details.component.ts"></script>
