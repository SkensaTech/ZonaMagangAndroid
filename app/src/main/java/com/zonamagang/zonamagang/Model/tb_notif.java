package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_notif
{
  private String ownerId;
  private String objectId;
  private java.util.Date updated;
  private Integer id_penerima;
  private java.util.Date created;
  private Integer id_notif;
  private Integer id_pengirim;
  private String isi_notif;
  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public Integer getId_penerima()
  {
    return id_penerima;
  }

  public void setId_penerima( Integer id_penerima )
  {
    this.id_penerima = id_penerima;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public Integer getId_notif()
  {
    return id_notif;
  }

  public void setId_notif( Integer id_notif )
  {
    this.id_notif = id_notif;
  }

  public Integer getId_pengirim()
  {
    return id_pengirim;
  }

  public void setId_pengirim( Integer id_pengirim )
  {
    this.id_pengirim = id_pengirim;
  }

  public String getIsi_notif()
  {
    return isi_notif;
  }

  public void setIsi_notif( String isi_notif )
  {
    this.isi_notif = isi_notif;
  }

                                                    
  public tb_notif save()
  {
    return Backendless.Data.of( tb_notif.class ).save( this );
  }

  public Future<tb_notif> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_notif> future = new Future<tb_notif>();
      Backendless.Data.of( tb_notif.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_notif> callback )
  {
    Backendless.Data.of( tb_notif.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_notif.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( tb_notif.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_notif.class ).remove( this, callback );
  }

  public static tb_notif findById( String id )
  {
    return Backendless.Data.of( tb_notif.class ).findById( id );
  }

  public static Future<tb_notif> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_notif> future = new Future<tb_notif>();
      Backendless.Data.of( tb_notif.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_notif> callback )
  {
    Backendless.Data.of( tb_notif.class ).findById( id, callback );
  }

  public static tb_notif findFirst()
  {
    return Backendless.Data.of( tb_notif.class ).findFirst();
  }

  public static Future<tb_notif> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_notif> future = new Future<tb_notif>();
      Backendless.Data.of( tb_notif.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_notif> callback )
  {
    Backendless.Data.of( tb_notif.class ).findFirst( callback );
  }

  public static tb_notif findLast()
  {
    return Backendless.Data.of( tb_notif.class ).findLast();
  }

  public static Future<tb_notif> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_notif> future = new Future<tb_notif>();
      Backendless.Data.of( tb_notif.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_notif> callback )
  {
    Backendless.Data.of( tb_notif.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_notif> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_notif.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_notif>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_notif>> future = new Future<BackendlessCollection<tb_notif>>();
      Backendless.Data.of( tb_notif.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_notif>> callback )
  {
    Backendless.Data.of( tb_notif.class ).find( query, callback );
  }
}